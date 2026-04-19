# 快速启动指南

## 环境准备清单

在启动项目之前，请确保以下服务已安装并运行：

### 1. Java 环境
- ✅ JDK 17 或更高版本
- 验证命令：`java -version`

### 2. Maven
- ✅ Maven 3.6 或更高版本
- 验证命令：`mvn -version`

### 3. MySQL 数据库
- ✅ MySQL 8.0 或更高版本
- 验证命令：`mysql --version`
- 确保 MySQL 服务正在运行

### 4. Redis 缓存
- ✅ Redis 6.0 或更高版本
- 验证命令：`redis-cli --version`
- 确保 Redis 服务正在运行

---

## 启动步骤

### 第一步：配置数据库

1. 登录 MySQL
```bash
mysql -u root -p
```

2. 执行初始化脚本（可选，系统会自动创建）
```sql
source init.sql
```

3. 修改配置文件 `src/main/resources/application.properties`
```properties
spring.datasource.username=root
spring.datasource.password=你的MySQL密码
```

### 第二步：启动 Redis

**Windows:**
```bash
redis-server
```

**Linux/Mac:**
```bash
sudo service redis-server start
# 或
brew services start redis
```

验证 Redis 是否运行：
```bash
redis-cli ping
# 应该返回 PONG
```

### 第三步：编译项目

在项目根目录执行：
```bash
mvn clean install
```

### 第四步：启动应用

```bash
mvn spring-boot:run
```

或者直接运行主类 `DemoApplication.java`

### 第五步：验证启动

打开浏览器访问：
```
http://localhost:8080/api/tasks
```

如果看到类似以下的响应，说明启动成功：
```json
{
  "success": true,
  "message": "操作成功",
  "data": {
    "content": [],
    "pageNumber": 0,
    "pageSize": 10,
    "totalElements": 0,
    "totalPages": 0,
    "first": true,
    "last": true,
    "empty": true
  },
  "timestamp": 1713500000000
}
```

---

## 常见问题排查

### 问题 1：无法连接 MySQL

**错误信息**: `Communications link failure`

**解决方案**:
1. 检查 MySQL 服务是否启动
2. 确认用户名和密码正确
3. 确认端口号正确（默认 3306）
4. 检查防火墙设置

### 问题 2：无法连接 Redis

**错误信息**: `Cannot get Jedis connection`

**解决方案**:
1. 检查 Redis 服务是否启动
2. 确认 Redis 端口（默认 6379）
3. 如果使用密码，配置 `spring.data.redis.password`

### 问题 3：端口被占用

**错误信息**: `Port 8080 was already in use`

**解决方案**:
修改 `application.properties`:
```properties
server.port=8081
```

### 问题 4：编译错误

**解决方案**:
1. 确认 JDK 版本为 17+
2. 清理 Maven 缓存：`mvn clean`
3. 重新下载依赖：`mvn dependency:purge-local-repository`

---

## API 测试示例

### 1. 创建任务

```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d "{
    \"title\": \"我的第一个任务\",
    \"description\": \"这是一个测试任务\",
    \"status\": \"pending\",
    \"priority\": \"high\",
    \"tags\": [\"测试\", \"学习\"]
  }"
```

### 2. 查询所有任务

```bash
curl http://localhost:8080/api/tasks?page=0&size=10
```

### 3. 查询单个任务

```bash
curl http://localhost:8080/api/tasks/1
```

### 4. 筛选任务

```bash
curl "http://localhost:8080/api/tasks/filter?status=pending&priority=high"
```

### 5. 更新任务

```bash
curl -X PUT http://localhost:8080/api/tasks/1 \
  -H "Content-Type: application/json" \
  -d "{
    \"status\": \"in_progress\"
  }"
```

### 6. 删除任务

```bash
curl -X DELETE http://localhost:8080/api/tasks/1
```

---

## 使用 Postman 测试

1. 下载并安装 [Postman](https://www.postman.com/)
2. 导入 API 集合（可创建新的 Collection）
3. 设置 Base URL: `http://localhost:8080/api/tasks`
4. 按照上述 API 文档创建请求

---

## 开发建议

### IDE 推荐
- **IntelliJ IDEA**（推荐）
- Eclipse
- VS Code + Java Extension Pack

### 调试技巧

1. **查看 SQL 日志**
   - 已在配置中启用 `spring.jpa.show-sql=true`
   - 控制台会显示所有执行的 SQL 语句

2. **查看应用日志**
   - 日志级别设置为 DEBUG
   - 可以跟踪任务操作流程

3. **Redis 监控**
   ```bash
   redis-cli
   > KEYS task:*
   > GET task:1
   ```

---

## 下一步

✅ 系统已成功启动  
✅ 开始开发新功能  
✅ 编写单元测试  
✅ 部署到生产环境  

祝开发顺利！🚀
