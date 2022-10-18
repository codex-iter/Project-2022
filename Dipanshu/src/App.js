import "./App.css";
import Navbar from "./components/Navbar";
import News from "./components/News";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import React from 'react'

const App = () => {
  return (
    <div className="App" >
      <Router>
        <Navbar />
        <Routes>
          <Route exact path="/NewsMine" element={<News key="homepage" />} />
          <Route exact path="/" element={<News key="home" />} />
          <Route
            exact
            path="/business"
            element={<News key="business" category="business" />}
          />
          <Route
            exact
            path="/technology"
            element={<News key="technology" category="technology" />}
          />
          <Route
            exact
            path="/health"
            element={<News key="health" category="health" />}
          />
          <Route
            exact
            path="/entertainment"
            element={<News key="entertainment" category="entertainment" />}
          />
          <Route
            exact
            path="/science"
            element={<News key="science" category="science" />}
          />
          <Route
            exact
            path="/sports"
            element={<News key="sports" category="sports" />}
          />
        </Routes>
      </Router>
    </div>
  )
}

export default App;