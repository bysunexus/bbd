<script setup lang="ts">
import {ref} from 'vue';

const emit = defineEmits<{
  (e: 'send', message: string): void
}>();

const message = ref('');
const sendDisabled = ref(false);

const handleSubmit = () => {
  if (message.value.trim()) {
    emit('send', message.value);
    message.value = '';
  }
};

const handleKeydown = (e: KeyboardEvent) => {
  if (e.key === 'Enter' && !e.shiftKey) {
    e.preventDefault();
    handleSubmit();
  }
};
const setValue = (value: string) => {
  message.value = value;
};
const setSendDisabled = (value: boolean) => {
  sendDisabled.value = value;
};
defineExpose({setValue, setSendDisabled});
</script>
<template>
  <el-input
    v-model="message"
    style="width: 75%"
    :rows="3"
    :disabled="sendDisabled"
    type="textarea"
    placeholder="请输入花费记录或者查询花费情况"
    @keydown="handleKeydown"
  />
  <el-button @click="handleSubmit" :disabled="!message.trim()||sendDisabled" type="primary" style="margin-left: 1rem">
    <el-icon :size="40">
      <Check/>
    </el-icon>
  </el-button>
</template>
<style scoped>
</style>
