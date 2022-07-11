import random
import pandas as pd
import numpy as np

data = pd.read_csv('books.csv', header=0)
name = ["book_id", "name", "author", "price", "ori_price", "category",
        "description", "publish", "publish_date", "img1", "img2", "img3", "img4",
        "img5", "img1_little", "img2_little", "img3_little", "img4_little", "img5_little",
        "comments"]
name1 = ["book_id", "price", "ori_price", "img1", "name"]
name2 = ["book_id", "img1", "img2", "img3", "img4", "img5", "description", "quantity",
         "author",  "publish", "publish_year", "publish_date", "category_id"]
name3 = ["category_id", "category_name"]
content1 = []
content2 = []
content3 = []
dicts = {}

def get_dict_key(dic, value):
    keys = list(dic.keys())
    values = list(dic.values())
    idx = values.index(value)
    key = keys[idx]
    return key

for one in data.index:
    lists = data.loc[one].values[:]
    if lists[5] not in dicts.values():
        dicts[len(dicts) + 1] = lists[5]
    cate_id = get_dict_key(dicts, lists[5])
    strs = lists[1] * 4
    year = lists[8].split('-')
    t1 = [lists[0], lists[3], lists[4], lists[9], lists[1]]
    t2 = [lists[0], lists[9], lists[10], lists[11], lists[12], lists[13], strs,
          str(random.randint(50, 3000)), lists[2], lists[7], year[0], lists[8],
          cate_id]
    content1.append(t1)
    content2.append(t2)
for i in dicts.keys():
    t3 = [str(i), dicts[i]]
    content3.append(t3)

output1 = pd.DataFrame(columns=name1, data=content1)
output1.to_csv('book_sorted.csv', index=False, encoding='utf-8-sig')
output2 = pd.DataFrame(columns=name2, data=content2)
output2.to_csv('book_detail_sorted.csv', index=False, encoding='utf-8-sig')
output3 = pd.DataFrame(columns=name3, data=content3)
output3.to_csv('book_category_sorted.csv', index=False, encoding='utf-8-sig')