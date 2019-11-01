# ImageLoader
## 单一原则
### v0.1
### v0.2
## 开闭原则
### v0.3
- 目的解决之前只能使用内存缓存的问题
- 新增DiskCache类以及向ImageLoader中添加了SD卡缓存的功能
```
ImageLoader imagerloader = new ImageLoader();
//使用SD卡缓存
imageLoader.useDiskCache(true);
//使用内存缓存
imageLoader.userDiskCache(false);
```
### v0.4
- 目的是解决用户使用内存缓存时不能使用SD卡缓存，使用SD卡缓存时不能使用内存缓存的问题
- 添加了DiskCache类以及修改了ImageLoader
- 缺点：if-else太多，结构脆弱，可拓展性差
