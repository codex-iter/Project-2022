import React, { useEffect, useState } from "react";
import NewsItem from "./NewsItem";
import Spinner from "./Spinner";
import logo from "./no-image.jpeg";
import PropTypes from "prop-types";
import InfiniteScroll from "react-infinite-scroll-component";

const News = (props) => {

  const [articles, setArticles] = useState([]);
  const [loading, setLoading] = useState(true);
  const [page, setPage] = useState(1)
  const [totalResults, setTotalResults] = useState(0);

  const suffix = " News || NewsMine - Best News App";
  document.title =
    props.category[0].toUpperCase() + props.category.slice(1) + suffix;


  const updateNews = async () => {
    const url = `https://newsapi.org/v2/top-headlines?country=${props.country}&category=${props.category}&apiKey=${props.apikey}&page=${page}&pageSize=${props.pageSize}`;
    setLoading(false);
    let data = await fetch(url);
    let parsedData = await data.json();
    setArticles(parsedData.articles);
    setLoading(false);
    setTotalResults(parsedData.totalResults);
  }

  useEffect(() => {
    updateNews();
  }, [])
  
  const fetchMoreData = async () => {
    const url = `https://newsapi.org/v2/top-headlines?country=${props.country}&category=${props.category}&apiKey=${props.apikey}&page=${page + 1}&pageSize=${props.pageSize}`;
    setPage(page + 1);
    let data = await fetch(url);
    let parsedData = await data.json();
    setArticles(articles.concat(parsedData.articles));
  };

  return (
    <>
      <h1
        className="text-center"
        style={{
          margin: "90px 0 20px 0",
          textDecoration: "underline",
        }}
      >
        Top Headlines from{" "}
        {`${props.category[0].toUpperCase() + props.category.slice(1)
          }`}
      </h1>
      {loading && <Spinner />}
      <InfiniteScroll
        dataLength={articles.length}
        next={fetchMoreData}
        hasMore={articles.length - 6 !== totalResults}
        loader={<Spinner />}
      >
        <div className="container">

          <div className="row">
            {articles.map((element) => {
              return (
                <div className="col-md-4" key={element.url}>
                  <NewsItem
                    title={element.title}
                    description={
                      element.description
                        ? element.description.slice(0, 80)
                        : "No desciption Provided"
                    }
                    imageUrl={element.urlToImage ? element.urlToImage : logo}
                    newsUrl={element.url}
                  />
                </div>
              );
            })}
          </div>
        </div>
      </InfiniteScroll>
    </>
  );
}

News.defaultProps = {
  country: "in",
  pageSize: 6,
  category: "general",
  apikey: "a3e2db6c19b747118416e93a3f7317d4",
};
News.propTypes = {
  country: PropTypes.string,
  pageSize: PropTypes.number,
  category: PropTypes.string,
  apikey: PropTypes.string.isRequired,
};

export default News;
