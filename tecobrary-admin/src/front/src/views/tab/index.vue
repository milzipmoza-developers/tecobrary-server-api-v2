<template>
  <div class="tab-container">
    <el-tag>mounted times ：{{ createdTimes }}</el-tag>
    <el-alert :closable="false" style="width:200px;display:inline-block;vertical-align: middle;margin-left:30px;"
              title="Tab with keep-alive" type="success"/>
    <el-tabs style="margin-top:15px;" type="border-card" v-model="activeName">
      <el-tab-pane :key="item.key" :label="item.label" :name="item.key" v-for="item in tabMapOptions">
        <keep-alive>
          <tab-pane :type="item.key" @create="showCreatedTimes" v-if="activeName==item.key"/>
        </keep-alive>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  import TabPane from './components/TabPane'

  export default {
    name: 'Tab',
    components: {TabPane},
    data() {
      return {
        tabMapOptions: [
          {label: 'China', key: 'CN'},
          {label: 'USA', key: 'US'},
          {label: 'Japan', key: 'JP'},
          {label: 'Eurozone', key: 'EU'}
        ],
        activeName: 'CN',
        createdTimes: 0
      }
    },
    watch: {
      activeName(val) {
        this.$router.push(`${this.$route.path}?tab=${val}`)
      }
    },
    created() {
      // init the default selected tab
      const tab = this.$route.query.tab
      if (tab) {
        this.activeName = tab
      }
    },
    methods: {
      showCreatedTimes() {
        this.createdTimes = this.createdTimes + 1
      }
    }
  }
</script>

<style scoped>
  .tab-container {
    margin: 30px;
  }
</style>
