import json
import csv

with open('books.csv', 'w', newline='', encoding='utf-8-sig') as csv_file:
    csv_writer = csv.writer(csv_file)
    csv_writer.writerow(["book_id", "name", "author", "price", "ori_price", "category",
                         "description", "publish", "publish_date", "img1", "img2", "img3", "img4",
                         "img5", "img1_little", "img2_little", "img3_little", "img4_little", "img5_little",
                         "comments"])
    with open('books.json', 'r', encoding="utf-8-sig") as jsonfile:
        data = json.load(jsonfile)
        for line_dict in data:
            id = str(line_dict['book_id'])
            name = str(line_dict['name'])
            author = str(line_dict['author'])
            price = str(line_dict['price'])
            ori_price = str(line_dict['ori_price'])
            category = str(line_dict['category'])
            description = str(line_dict['description'])
            publish = str(line_dict['publish'])
            publish_date = str(line_dict['publish_date'])
            img1 = str(line_dict['img1'])
            img2 = str(line_dict['img2'])
            img3 = str(line_dict['img3'])
            img4 = str(line_dict['img4'])
            img5 = str(line_dict['img5'])
            img1_little = str(line_dict['img1_little'])
            img2_little = str(line_dict['img2_little'])
            img3_little = str(line_dict['img3_little'])
            img4_little = str(line_dict['img4_little'])
            img5_little = str(line_dict['img5_little'])
            csv_writer.writerow([id, name, author, price, ori_price, category,
                                 description, publish, publish_date, img1, img2, img3, img4,
                                 img5, img1_little, img2_little, img3_little, img4_little, img5_little])
