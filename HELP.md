## 逼逼叨

目前是简单的spring ai 集成测试demo

1. 集成spring ai
2. 使用本地ollama测试
3. 简单的聊天界面 http://localhost:8080/
4. 也可使用命令行进行交互

### 依赖环境

1. jdk 21
2. ollama
3. qwen2.5:7b 或其他支持tool的模型

### 2025年4月16日

1. 将spring ai 升级为 1.0.0-M7
2. 使用openai sdk进行集成（openai sdk比较通用各大平台基本都支持）
3. mcp client集成测试 （需要安装npx，根据安装目录修改[mcp-servers-config.json](src/main/resources/mcp-servers-config.json)
   文件）

### 2025年4月21日

1. 由于系统负载问题导致计算机蓝屏，本地的Ollama调用改为使用[火山引擎](https://www.volcengine.com/)的免费额度进行测试
2. 修改ChatClient的配置，统一构建chatClient，避免每次创建chatClient时都需要设置系统提示语和工具
3. 