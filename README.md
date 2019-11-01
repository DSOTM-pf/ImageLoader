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
