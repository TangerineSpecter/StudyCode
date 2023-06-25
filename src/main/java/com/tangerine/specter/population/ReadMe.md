# 目录
- [v1.0 出生和死亡](#v10-出生和死亡)
- [v1.1 婚姻](#v11-婚姻)

## 人口趋势演示

**前提条件：**

1. 时间以2020年开始。
2. 初始人口10000人，男女各占50%，初始20岁。
3. 2020年中国人均寿命77.3岁，向上取整算78岁。
4. 模拟100个世纪增长趋势。


### v1.0 出生和死亡
> 只考虑出生和死亡，一个家庭生一胎，每年增长人数为当前未生育人口的50%。78岁自然死亡。

**数据来源：**
78岁

### v1.1 婚姻
> 考虑到新生人口只能到法定婚姻年龄才能产生下一代，引入婚姻条件。

5. 法定婚姻年龄男性22岁，女性20岁。
6. 2020年中国的平均结婚年龄为男性25.9岁，女性24.4岁。
结论：向上取最大值整数，以26岁为核算标准。

### v1.2 出生比例
> 由于出生的男女比例不对等，则引入出生比例作为配对标准。

7. 2020年中国人口生育男女比率为105.3，即每出生100个女婴，相应地有105.3个男婴出生。
8. 心理学家认为婚姻年龄差距应该在5岁以内。
结论：每个同龄存在5个男性溢出，为保证人口利用率最大化，占比向下取整按照2%作为核算标准，以保证对等匹配。

### v1.3 收入和房贷
> 由于婚姻的前提是物质的保障，则引入收入和房贷的条件，以房子作为前提。

9. 2020年全国居民人均可支配收入，平均32189元，中位数27540元。
10. 由于婚姻主要集中在年轻人，则以北京75602元、上海76437元、广东50257元、浙江62699元，四川38253元 等年轻人居多的城市为准，取平均收入取整为60650元。