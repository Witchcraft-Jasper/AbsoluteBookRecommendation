# -*- coding: utf-8 -*-
from scrapy import cmdline

# 执行基于xpath的spider
cmdline.execute('scrapy crawl spider_xpath_douban'.split())

# 执行基于css的spider
# cmdline.execute('scrapy crawl spider_xpath_douban'.split())
