import scrapy

from Dang.items import BookDangdang


class BookSpider(scrapy.Spider):
    name = 'book'
    allowed_domains = ['bang.dangdang.com']
    start_urls = ['http://bang.dangdang.com/books/']

    def parse(self, response):
        for cate in response.xpath('/html/body/div[2]/div[9]/div[2]/ul/li'):
            href = cate.xpath('./div/a/@href').extract_first().replace('\n', '').strip()
            category = cate.xpath('./p/a/text()').extract_first().replace('\n', '').strip()
            # print('正在爬取分类：', category, '...')
            yield scrapy.Request(url=href, callback=self.parse_cate, dont_filter=True,
                                 meta={'category': category, 'href': href})

    def parse_cate(self, response):
        print('crawling cate...')
        parent_href = response.meta['href']
        parent_cate = response.meta['category']
        for page in range(25):
            print('crawling page:', page + 1, '/25')
            url = parent_href[:-1] + str(page)
            yield scrapy.Request(url=url, callback=self.parse_detail, dont_filter=True, meta={'category': parent_cate})

    def parse_detail(self, response):
        print('crawling detail...')

        items = []
        for book in response.xpath('/html/body/div[2]/div[3]/div[2]/ul/li'):
            item = BookDangdang()
            item['book_id'] = (book.xpath('./div[2]/a/@href').extract_first().replace('\n', '').strip())[-13:-5]
            item['name'] = book.xpath('./div[3]/a/text()').extract_first().replace('\n', '').strip()
            item['author'] = book.xpath('./div[5]/a/text()').extract_first().replace('\n', '').strip()
            item['price'] = book.xpath('./div[7]/p[1]/span[1]/text()').extract_first().replace('\n', '').strip()
            item['ori_price'] = book.xpath('./div[7]/p[1]/span[2]/text()').extract_first().replace('\n', '').strip()
            item['category'] = response.meta['category']
            item['description'] = ''
            item['publish'] = book.xpath('./div[6]/a/text()').extract_first().replace('\n', '').strip()
            item['publish_date'] = book.xpath('./div[6]/span/text()').extract_first().replace('\n', '').strip()
            img = book.xpath('./div[2]/a/img/@src').extract_first().replace('\n', '').strip()
            print(img)
            img = img.split('-')
            img_little = img[1].split('_')
            img_little = '_s_' + img_little[2]
            print(img[0] + '-1' + (img[1])[1:])
            item['img1'] = img[0] + '-1' + (img[1])[1:]
            item['img2'] = img[0] + '-2' + (img[1])[1:]
            item['img3'] = img[0] + '-3' + (img[1])[1:]
            item['img4'] = img[0] + '-4' + (img[1])[1:]
            item['img5'] = img[0] + '-5' + (img[1])[1:]
            item['img1_little'] = img[0] + '-1' + img_little
            item['img2_little'] = img[0] + '-2' + img_little
            item['img3_little'] = img[0] + '-3' + img_little
            item['img4_little'] = img[0] + '-4' + img_little
            item['img5_little'] = img[0] + '-5' + img_little
            item['comments'] = ''
            items.append(item)

            yield item
