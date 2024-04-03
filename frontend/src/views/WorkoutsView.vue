<script lang="ts" setup>

import { useAuthStore } from '@/stores/auth';
import { onMounted, ref } from 'vue';
import router from '@/router';

const store = useAuthStore();
const token = store.token;


const workouts = ref<W[]>([]);
// const x = ref("");

class E {
  id: number = 0;
  name: String = "";
  description: String = "";
  type: String = "";
}

class W {
  id: number = 0;
  name: String = "";
  exercises: E[] = [];
}


onMounted(async () => {
  const response2 = await fetch('http://localhost:8080/list-all-workouts', {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  }).then(res => res.json())
  .then(arr => {
    // x.value = arr;
    workouts.value = arr as W[];
  }
  );

});

</script>

<template>
    
  <div>
    <div v-if="workouts.length > 0">
      <div v-for="(item, index) in workouts" :key="index">
        <div style="border: 3px solid black; height: auto;">
          <label style="font-size: 30px; font-weight: bold; padding-left: 30px;">{{ item.name }} </label><br>
          <!-- <label style="font-size: 20px; font-weight: bold; padding-left: 30px;">{{ item.exercises }}</label><br> -->

          <div v-for="(item2, index2) in item.exercises" :key="index">
            <label style="font-size: 30px; font-weight: bold; padding-left: 30px;"> {{ index2 + 1}}. {{ item2.name }} </label><br>
            <label style="font-size: 20px; font-weight: bold; padding-left: 30px;">{{ item2.description }}</label><br>
          </div>

        </div>
      </div>
    </div>
    <div v-else>
      <p style="font-size: 30px;">You dont have saved workouts</p>
    </div>
  </div>

</template>
