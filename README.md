# RIABandwidthSaver

🍃 EcoMode ON

## 概述

RIABandwidthSaver 是一个基于 ProtocolLib 的节流插件，旨在在玩家处于 AFK 状态期间避免发送不必要的数据包和区块，以缓解服务器的带宽和流量资源消耗的问题。  
目前仅支持与 CMI 配合工作。 * 作为 AFK 状态提供插件

## 功能

* 降低处于 AFK 状态玩家的客户端视野距离，不影响服务器的 Tick 视野距离（模拟距离），以便减少 AFK 玩家的流量消耗（越小的视距=越少的数据包=越小的流量消耗）
* 抑制或减少数据包发送：
  * （取消）动画数据包
  * （取消）方块破坏动画数据包
  * （取消）实体声音数据包
  * （取消）音效数据包
  * （取消）粒子效果数据包
  * （取消）爆炸动画数据包
  * （取消）世界时间同步数据包
  * （取消）实体头部旋转数据包
  * （取消）受伤动画数据包
  * （取消）伤害事件数据包
  * （取消）实体查看数据包
  * （减少）实体移动数据包
  * （减少）实体移动和视角数据包
  * （减少）经验球生成数据包
  * （减少）载具移动数据包
  * （取消）方块动作数据包
  * （取消）光照更新数据包
  * （取消）视角看向数据包
  * （取消）TAB列表头部和尾部文本更新数据包
  * （取消）世界事件数据包
  * （取消）物品、投掷物、实体捡起动画数据包
  * （取消）自定义声音数据包
  * （取消）非玩家背包容器GUI物品槽位更改数据包
* 流量统计（节省与消耗）

## 演示

![image](https://github.com/RIA-AED/RIABandwidthSaver/assets/30802565/521cbe70-7728-4c78-adf2-cdf9f0d54b88)  
![image](https://github.com/RIA-AED/RIABandwidthSaver/assets/30802565/518f2bf7-13b7-44b1-a792-71ae3e6f5b83)  
![image](https://github.com/RIA-AED/RIABandwidthSaver/assets/30802565/0fdf5cf3-d7ec-4e0c-98cc-3258652d6217)

## 命令

```
/riabandwidthsaver - 查看流量节省统计数据
/riabandwidthsaver unfiltered - 查看流量消耗统计数据
/riabandwidthsaver reload - 重载配置文件并重新注册数据包监听器
```

## 配置文件

```yaml
# 计算所有数据包（即启用 /riabandwidthsaver unfiltered 的统计信息）
calcAllPackets: true
```

