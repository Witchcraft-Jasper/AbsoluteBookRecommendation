# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy


class BookDangdang(scrapy.Item):
    href = scrapy.Field() # 书籍url
    book_id = scrapy.Field() # 书籍id
    category = scrapy.Field() # 书籍分类
    img1 = scrapy.Field() # 大图1url
    img2 = scrapy.Field() # 大图2url
    img3 = scrapy.Field() # 大图3url
    img4 = scrapy.Field() # 大图4url
    img5 = scrapy.Field() # 大图5url
    img1_little = scrapy.Field() # 小图1url
    img2_little = scrapy.Field() # 小图2url
    img3_little = scrapy.Field() # 小图3url
    img4_little = scrapy.Field() # 小图4url
    img5_little = scrapy.Field() # 小图5url
    description = scrapy.Field() # 详细描述
    name = scrapy.Field() # 书籍名称
    price = scrapy.Field() # 现价
    ori_price = scrapy.Field() #原价
    author = scrapy.Field() # 作者
    publish_date = scrapy.Field() # 出版日期
    publish = scrapy.Field() # 出版社
    comments = scrapy.Field() # 评论
