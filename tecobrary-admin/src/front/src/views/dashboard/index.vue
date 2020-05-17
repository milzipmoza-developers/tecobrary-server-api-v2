<template>
  <div class="dashboard-container">
    <div class="dashboard-editor-container">
      <p>테코브러리 어드민 대시보드</p>
      <p>권한 문의는 우아한테크코스 슬랙에서 해주세요.</p>
    </div>
  </div>
</template>

<script>

  export default {
  name: 'Dashboard',
  data() {
    return {
    }
  },
}
</script>

<style lang="scss" scoped>
  .emptyGif {
    display: block;
    width: 45%;
    margin: 0 auto;
  }

  .dashboard-editor-container {
    background-color: #e3e3e3;
    min-height: 100vh;
    padding: 50px 60px 0px;
    .pan-info-roles {
      font-size: 12px;
      font-weight: 700;
      color: #333;
      display: block;
    }
    .info-container {
      position: relative;
      margin-left: 190px;
      height: 150px;
      line-height: 200px;
      .display_name {
        font-size: 48px;
        line-height: 48px;
        color: #212121;
        position: absolute;
        top: 25px;
      }
    }
  }
</style>
