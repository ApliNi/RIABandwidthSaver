# RIABandwidthSaver

🍃 EcoMode ON

## 概述

RIABandwidthSaver 是一个基于 ProtocolLib 的节流插件，旨在在玩家处于 AFK 状态期间避免发送不必要的数据包和区块，以缓解服务器的带宽和流量资源消耗的问题。

* **你需要安装 ProtocolLib 才能使用此插件**
* 目前仅支持 EssentialsX 和 CMI 作为 AFK 状态提供者，你需要至少安装这两个中的一个才能使用
* 提供的数据信息未压缩之前的流量，实际情况下，您的服务器很可能配置了网络数据包压缩，这种情况下统计流量和实际流量会有较大出入。

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
  * （取消）实体药水效果更新数据包
* 流量统计（节省与消耗）

## 演示

![Snipaste_2023-11-26_03-00-32](https://github.com/RIA-AED/RIABandwidthSaver/assets/30802565/35c3336e-edf9-4a54-b075-0553809de505)
![image](https://github.com/RIA-AED/RIABandwidthSaver/assets/30802565/a1b8c7f1-8067-4abd-b0b9-41f2da8c48a2)
![image](https://github.com/RIA-AED/RIABandwidthSaver/assets/30802565/2dd8e3fa-c6df-4669-a5e9-cb1c832cf2f2)

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

