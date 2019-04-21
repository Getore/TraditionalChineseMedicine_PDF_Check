
jieba.load_userdict("F:\Trainee\pycharm-professional\myTest.txt")

text = "故宫的著名景点包括乾清宫、太和殿和黄琉璃瓦等"

# 全模式
seg_list = jieba.cut(text, cut_all=True)
print(u"[全模式]: ", "/ ".join(seg_list))

# 精确模式
seg_list = jieba.cut(text, cut_all=False)
print(u"[精确模式]: ", "/ ".join(seg_list))

# 搜索引擎模式
seg_list = jieba.cut_for_search(text)
print(u"[搜索引擎模式]: ", "/ ".join(seg_list))