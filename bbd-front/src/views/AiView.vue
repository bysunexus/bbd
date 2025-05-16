<script setup lang="ts">
import {ref} from 'vue';
import ChatMessage from "@/components/ChatMessage.vue";
import ChatInput from "@/components/ChatInput.vue";
import axios from 'axios';


interface Message {
  data: any;
  dataType: 'text' | 'ledger' | 'stat' | 'tip';
  role: 'Me' | '叨';
}

const messages = ref<Message[]>([{
  data: {
    content: "你好，我是逼逼叨记账小助手，你可以跟我对话完成记账和统计。下面给出了一些例子：",
    tips: ["昨天买了五斤苹果花费20.5元", "充值公交卡12元", "最近10天的账本统计", "上周的花费情况"]
  },
  dataType: 'tip',
  role: '叨'
}]);

const handleSendMessage = (content: string) => {
  // 添加用户消息
  messages.value.push({
    data: {
      content: content
    },
    dataType: 'text',
    role: 'Me'
  });

  let length = messages.value.push({
    data: {
      content: "处理中请稍后..."
    },
    dataType: 'text',
    role: '叨'
  });
  let isLoading = false, jsonData = false, resultData = "";
  let aiMessage = messages.value[length - 1];
  // 发送消息给服务器
  axios({
    method: 'post',
    url: '/bbd-server/ai/chat',
    data: {
      message: content
    },
    responseType: 'stream',
    adapter: 'fetch'
  }).then(async (response) => {
    const stream = response.data;
    const reader = stream.pipeThrough(new TextDecoderStream()).getReader();
    while (true) {
      const {value, done} = await reader.read();
      if (done) {
        break;
      }
      if (value && value.trim()) {
        console.log(value);

        if (!isLoading) {
          isLoading = true;
          // 处理数据 json 或者是文本
          if (value.startsWith('{')) {
            jsonData = true;
          }
          if (!jsonData) {
            aiMessage.data.content = "";
          }
        }

        if (jsonData) {
          resultData += value;
        } else {
          aiMessage.data.content += value;
        }
      }

      if (jsonData) {
        let json = JSON.parse(resultData);
        aiMessage.dataType = json.dataType;
        aiMessage.data = json.data;
      }
    }
  }).catch(function (error) {
    aiMessage.data.content = "请求服务器出错,请稍后再试!";
  });
};

const chatInputRef = ref<InstanceType<typeof ChatInput>>()

const handleTipClick = (tip: string) => {
  chatInputRef.value?.setValue(tip);
};
</script>

<template>
  <div class="bbd-fullheight">
    <el-container class="bbd-fullheight">
      <el-main class="bbd-fullheight">
        <el-scrollbar>
          <ChatMessage
            v-for="(message, index) in messages"
            :key="index"
            :data="message.data"
            :dataType="message.dataType"
            :role="message.role"
            @tip-click="handleTipClick"
          />
        </el-scrollbar>

      </el-main>
      <el-footer height="200px">
        <ChatInput ref="chatInputRef" @send="handleSendMessage"/>
      </el-footer>
    </el-container>
  </div>
</template>

