import random

import pandas as pd

content = []
comentList = ["高颜值宝贝，简直令人爱不释手。内容更不用说，实在是好。",
              "很喜欢看的一本书，语言诙谐有趣，生动描绘出猫的特点，非常好看的书，推荐阅读",
              "最近学习有点忙，没来的及看，但是封面好好看啊！！里面赠了一本作者的人生经历",
              "还是夏目漱石的书本来在微信读书上也能看但是纸质书的喜悦永远无法替代。",
              "因为喜欢肖战，看了很多他看过，他推荐，和他有关的书。",
              "陌上青草，帘外芭蕉，沉默的烟雨红尘。繁华与萧瑟对望，沧海与桑田为邻。整个世界都在真与幻之间摇摇晃晃。不变的是匆匆赶路却又从未走远的时光！",
              "里面很多观点，段落都是几乎原封不动的摘抄复制，比如直接摘抄复制《佛畏系统》等等，不一一列举了，几乎无自我观点，商业推广太浓，大杂烩。",
              "这本书写的透彻，主题清晰。语言简练！收到书后连着看完第一章。很有续读魅力！",
              "看电视剧，里面人物推荐，我才买的，刚读完第一个故事，迟开的花朵，就深深陷进去了，两天没缓过劲儿来…还有一个官员之死，就一个喷嚏就吓死了，太讽刺了…还没看完，继续…",
              "纸质不错，排版美观，字的大小看着也很舒服，至于内容还没读，应该不错。",
              "拿到书 就迫不及待的看了 只是外包装有些磕破了 在这里买了很多书 这本书真的是很不一样 与众不同啊",
              "虽然我没有孩子，但我还是觉得此书对孩子有帮助，遗憾的是有一小点怼折，美中不足。",
              "经典小说了，高中时候老师也很推荐读的，现在读读又有不一样的感受了",
              "购书的一次失误，没看清楚说明，不是一篇小说的具体内容，而是一本合集。",
              "印象最深刻的是犯罪前后的紧张感，像本人所说的他杀死的其实是自己。",
              "如果我可以年轻时候就有这本书，那该有多幸运。珍惜吧，年轻人。好好读，经常读。在饭爷影响下，我已经读了很多金融类和管理类书，受益匪浅。现在读书不是我需要对抗的熵增，而是巨大的安全感。",
              "从公众号追到微博，再到买书 希望一直写下去 有了百万粉丝也不要膨胀哈",
              "很不错, 整了两本, 一本自己看, 一本送人, 确实有很多东西, 如果没人告诉你,你是一辈子也不会明白的, 你连怎么去思考这东西,或者找到切入点,很难, 人往往局限与自己的眼界格局或者说是经验",
              "东西不错的",
              "连续读完三本书，整个人都感觉得到了一种升华，感觉真实存在的世界，仿佛就在我身边，回味无穷的故事。"]
base_str = 'ABCDEFGHIGKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz'
scores = ['5', '5', '4', '5', '4', '3', '2', '1', '5', '4', '1', '3']
name = ["user_id", "comment_time", "book_id", "score", "content"]
scoreList = []
data = pd.read_csv('new_data/book_sorted.csv', header=0)


def geneTime():
    years = ['2020', '2021', '2022']
    time = "".join(random.choice(years))
    month = random.randint(1, 12)
    day = random.randint(1, 28)
    hour = random.randint(1, 24)
    mins = random.randint(1, 59)
    s = random.randint(1, 59)
    if month < 10:
        month = "0" + str(month)
    if day < 10:
        day = "0" + str(day)
    if hour < 10:
        hour = "0" + str(hour)
    if mins < 10:
        mins = "0" + str(mins)
    if s < 10:
        s = "0" + str(s)
    time = time + str("-" + str(month) + "-" + str(day) + " ")
    time = time + str(str(hour) + ":" + str(mins) + ":" + str(s))
    return time
lens = len(data)

for i in range(0, 150000):
    book_id = data.loc[random.randint(0, lens - 1)].values[:][0]
    user_id = random.randint(1, 19999)
    scoresTarget = str(book_id) + "+" + str(user_id)
    if scoresTarget in scoreList:
        i -= 1
        continue
    scoreList.append(scoresTarget)
    tempList = [str(user_id), geneTime(), book_id, random.choice(scores), random.choice(comentList)]
    content.append(tempList)
output = pd.DataFrame(columns=name, data=content)
output.to_csv('comment_sorted.csv', index=False, encoding='utf-8-sig')
