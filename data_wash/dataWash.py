import pandas as pd
import numpy as np

data = pd.read_csv('E:\\homework\\大数据小学期\\数据库文件\\book_category.csv', header=0)
name = ['category_id', 'name']
content = []

for one in data.index:
    lists = data.loc[one].values[0:3]
    tempList = [lists[0], lists[2]]
    content.append(tempList)

output1 = pd.DataFrame(columns=name, data=content)
output1.to_csv('book_category_sorted.csv', index=False, encoding='utf-8-sig')

data = pd.read_csv('E:\\homework\\大数据小学期\\数据库文件\\book_info.csv', header=0)
name = ['book_id', 'category_id', 'name', 'price', 'ori_price', 'img', 'author', 'year', 'press']
content = []

for one in data.index:
    lists = data.loc[one].values[:]
    tempList = [lists[0], lists[1], lists[3], lists[13], lists[16], lists[21], lists[10], lists[7], lists[6]]
    content.append(tempList)

output1 = pd.DataFrame(columns=name, data=content)
output1.to_csv('book_info_sorted.csv', index=False, encoding='utf-8-sig')

data = pd.read_csv('E:\\homework\\大数据小学期\\数据库文件\\orders.csv', header=0)
name = ['order_id', 'user_id', 'date', 'status', 'payment']
content = []

for one in data.index:
    lists = data.loc[one].values[0:7]
    tempList = [lists[0], lists[1], lists[6], lists[4], lists[2]]
    content.append(tempList)

output1 = pd.DataFrame(columns=name, data=content)
output1.to_csv('orders_sorted.csv', index=False, encoding='utf-8-sig')

data = pd.read_csv('E:\\homework\\大数据小学期\\数据库文件\\order_detail.csv', header=0)
name = ['detail_id', 'order_id', 'book_id', 'amount', 'price']
content = []

for one in data.index:
    lists = data.loc[one].values[0:7]
    tempList = [lists[0], lists[2], lists[3], lists[5], lists[6]]
    content.append(tempList)

output1 = pd.DataFrame(columns=name, data=content)
output1.to_csv('order_detail_sorted.csv', index=False, encoding='utf-8-sig')

data = pd.read_csv('E:\\homework\\大数据小学期\\数据库文件\\user.csv', header=0)
name = ['user_id', 'username', 'password', 'gender']
content = []

for one in data.index:
    lists = data.loc[one].values[0:5]
    tempList = [lists[0], lists[1], lists[3], lists[4]]
    content.append(tempList)

output1 = pd.DataFrame(columns=name, data=content)
output1.to_csv('user_sorted.csv', index=False, encoding='utf-8-sig')
