import csv
import random

import pandas as pd

content1 = []
content2 = []
userlist = []
base_str ='ABCDEFGHIGKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz'
address = "北京市海淀区北下关街道北京交通大学"
phone = "12345678912"
name1 = ["user_id", "username", "password"]
name2 = ["user_id", "gender", "age", "phone", "address"]
for k in range(0, 20000):
    str1 =[random.choice(base_str) for i in range(5)]
    username = "".join(str1)
    if username not in userlist:
        userlist.append(username)
    else:
        k -= 1
for i in range(0, len(userlist)):
    password = [random.choice(base_str) for j in range(8)]
    password = "".join(password)
    age = random.randint(12, 75)
    tempList1 = [str(i + 1), userlist[i], password]
    tempList2 = [str(i + 1), str(random.randint(1, 2)), str(age), phone, address]
    content1.append(tempList1)
    content2.append(tempList2)
output1 = pd.DataFrame(columns=name1, data=content1)
output1.to_csv('user_sorted.csv', index=False, encoding='utf-8-sig')
output2 = pd.DataFrame(columns=name2, data=content2)
output2.to_csv('user_detail_sorted.csv', index=False, encoding='utf-8-sig')



