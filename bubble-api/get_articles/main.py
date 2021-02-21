import numpy as np
from newspaper import Article
from newspaper.article import ArticleException
import json
import requests
import re
import datetime
from newsapi import NewsApiClient
# import nltk
# nltk.download('wordnet')
# nltk.download('punkt')

# from nltk.stem import WordNetLemmatizer 
  
# lemmatizer = WordNetLemmatizer() 

API_KEY = '77d7cb4756d44e13aea4a50d033d27e3'
NEWS_API = NewsApiClient(api_key=API_KEY)
MINIMUM_ARTICLE_CHAR_LENGTH = 650
# note that this is order-dependent ('s must come before ' and ... comes before ..)
BAD_CHARS = ['\r', '\n', '"', '`', '\'s', '\'', '...', '..']
DOMAINS = 'fortune.com,time.com,cnn.com,cbsnews.com,cnbc.com,' \
          'huffingtonpost.com,msnbc.com,nbcnews.com,usatoday.com/news/,wsj.com,abcnews.go.com,' \
          'apnews.com,news.google.com,politico.com,washingtonpost.com,washingtontimes.com' \
          'latimes.com,nytimes.com,theatlantic.com,npr.org,nypost.com,chicago-tribune.com' \
          'wired.com,vox.com'

def get_articles(request):
    """Responds to any HTTP request.
    Args:
        request (flask.Request): HTTP request object.
    Returns:
        The response text or any set of values that can be turned into a
        Response object using
        `make_response <http://flask.pocoo.org/docs/1.0/api/#flask.Flask.make_response>`.
    """
#     request_json = request.get_json()
    arg_dict = {"start": "2021-02-01", "end": "2021-02-21", "num": 1}

#     for k, v in arg_dict.items():
#         if request.args and k in request.args:
#             arg_dict[k] = request.args.get(k)
#         elif request_json and k in request_json:
#             arg_dict[k] = request_json[k]


    top_headlines = NEWS_API.get_everything(from_param=arg_dict['start'],
                                            to=arg_dict['end'],
                                            language='en',
                                            domains=DOMAINS,
                                            page_size=arg_dict['num'])

    articles_json = []
    articles = top_headlines['articles']
    for i, a in enumerate(articles):
        url = a['url']
        try:
            article = Article(url, keep_article_html=True)
            article.download()
            article.parse()
            # threshold
            text = article.text
            for char in BAD_CHARS:
                text = text.replace(char, '')
            text = re.sub(r'([a-z])\.([A-Z])', r'\1. \2', text)
            # word_list = nltk.word_tokenize(text)
            # word_list = [lemmatizer.lemmatize(w) for w in word_list]
            if len(text) >= MINIMUM_ARTICLE_CHAR_LENGTH:
                date = datetime.datetime.strptime(a['publishedAt'], "%Y-%m-%dT%H:%M:%SZ")
                date = date.strftime("%d-%m-%Y %H:%M")
                print(date)
                articles_json.append(
                    {
                'title': article.title,
                'url': a['url'],
                'timePublished': date,
                'sourceName': a['source']['name'],
                'rawContent': text
                })
        except ArticleException:
            pass
    step2 = {"items": articles_json}
    final = json.dumps(step2)
    requests.post("http://localhost:8080/articles/bulk", json = final, headers = {"Content-Type":
    "application/json"})
    return final


if __name__ == '__main__':
    get_articles({})
