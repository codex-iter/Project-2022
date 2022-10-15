import requests
from bs4 import BeautifulSoup as bs
from random import choice


def scrape():
    headers = {
        'user-agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36'}
    url = 'https://wall.alphacoders.com/search.php?search=anime+girl'
    main_url = 'https://wall.alphacoders.com/'
    soup = _extracted_from_scrape_6(url, headers)
    alist = [
        i.get('href')
        for i in soup.findAll('a')
        if 'big.php?i=' in repr(i.get('href'))
    ]

    final_link = main_url+str(choice(alist))
    soup2 = _extracted_from_scrape_6(final_link, headers)
    # a = ''
    for i in soup2.findAll('img'):
        if 'main-content' in str(i.get('class')):
            return str(i.get('src'))


def _extracted_from_scrape_6(arg0, headers):
    r = requests.get(arg0, headers=headers)
    if r.status_code != 200:
        print('shit internet')
        exit()
    return bs(r.content, 'html.parser')
