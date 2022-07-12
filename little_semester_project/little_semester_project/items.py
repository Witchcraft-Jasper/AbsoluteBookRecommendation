# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy


class LittleSemesterProjectItem(scrapy.Item):
    # 图书id
    isbn = scrapy.Field()
    # 书名
    title = scrapy.Field()
    # 作者
    author = scrapy.Field()
    # 出版日期
    pub_date = scrapy.Field()
    # 出版社
    publisher = scrapy.Field()
    # 小图
    s_img = scrapy.Field()
    # 中图
    m_img = scrapy.Field()
    # 大图
    b_img = scrapy.Field()
    # 类别id
    category_id = scrapy.Field()
    # 类别名称
    category = scrapy.Field()
    # 单价
    price = scrapy.Field()
    # 图书一句话描述
    scrible = scrapy.Field()
    # 图书详情页的url
    sub_url = scrapy.Field()
pass
