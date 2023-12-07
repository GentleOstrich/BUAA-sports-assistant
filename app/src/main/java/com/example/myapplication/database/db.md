# 数据库设计文档

数据库设计：

1. 用户表：integer id，String account, String password 

2. 运动记录表：用户表.id，String type，运动时间，卡路里......

3. 团体组局：integer game_Id，用户表.id(组局发起者)

4. 
组局参与者表(多对多)：integer game_Id，用户表.id(组局参与者)

5. 攻略表：用户表.id(发布者)，String title，String content，图片...
