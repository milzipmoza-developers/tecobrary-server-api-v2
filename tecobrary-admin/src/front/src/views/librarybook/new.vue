<template>
  <div class="app-container">
    <el-form :inline="true" :model="queryForm">
      <el-form-item label="검색어" width="100">
        <el-input v-model="queryForm.keyword"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSearchRequest">검색</el-button>
      </el-form-item>
      <el-form-item v-if="searchBooks.selection">
        <el-button type="success" @click="enrollDialogBtnClickHandler">등록</el-button>
      </el-form-item>
    </el-form>
    
    <el-table
      empty-text="검색 결과가 없습니다."
      ref="searchBookTable"
      :data="searchBooks.content"
      style="width: 100%; min-height: 400px;"
      highlight-current-row
      fit
      @current-change="handleSelectionChange"
    >
      <el-table-column label="제목">
        <span slot-scope="scope" class="link-type" v-html="scope.row.title"
              @click="onClickTitle(scope.$index)"
        ></span>
      </el-table-column>
      <el-table-column align="center" label="작가">
        <span slot-scope="scope" v-html="scope.row.author"></span>
      </el-table-column>
      <el-table-column align="center" label="출판사">
        <span slot-scope="scope" v-html="scope.row.publisher"></span>
      </el-table-column>
      <el-table-column align="center" label="ISBN">
        <span slot-scope="scope" v-html="scope.row.isbn"></span>
      </el-table-column>
    </el-table>
  
    <!-- 다이얼로그 -->
    <el-dialog title="도서 등록 정보 확인" :visible.sync="enroll.dialog">
      <el-form :model="enroll.book">
        <el-form-item label="제목" label-width="120px">
          <el-input v-model="enroll.book.title"></el-input>
        </el-form-item>
        <el-form-item label="저자" label-width="120px">
          <el-input v-model="enroll.book.author"></el-input>
        </el-form-item>
        <el-form-item label="출판사" label-width="120px">
          <el-input v-model="enroll.book.publisher"></el-input>
        </el-form-item>
        <el-form-item label="이미지" label-width="120px">
          <el-input v-model="enroll.book.image" disabled></el-input>
        </el-form-item>
        <el-form-item label="ISBN" label-width="120px">
          <el-input v-model="enroll.book.isbn" disabled></el-input>
        </el-form-item>
        <el-form-item label="책 설명" label-width="120px">
          <el-input type="textarea" v-model="enroll.book.description"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="enroll.dialog = false">취소</el-button>
        <el-button type="primary" @click="enrollRequest">등록</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import {Message} from 'element-ui'
  import {searchBook} from "@/api/naverApi";
  import {createLibraryBook} from "@/api/libraryBook"

  export default {
    data: () => ({
      queryForm: {
        keyword: '',
        page: 1,
        number: 10,
      },
      searchBooks: {
        content: [],
        selection: null
      },
      enroll: {
        dialog: false,
        book: {
          image: '',
          title: '',
          author: '',
          publisher: '',
          isbn: '',
          description: '',
        }
      }
    }),

    methods: {
      async onSearchRequest() {
        if (this.queryForm.keyword.length === 0) {
          Message.warning("검색어를 입력해주세요.")
          return;
        }
        try {
          const response = await searchBook(this.queryForm)
          this.searchBooks.content = response
        } catch (e) {
          console.log(e)
        }
      },

      onClickTitle(index) {
        const url = this.searchBooks.content[index].link;
        const win = window.open(url, '_blank');
        win.focus();
      },

      handleSelectionChange(val) {
        console.log(val)
        this.searchBooks.selection = val;
      },

      enrollDialogBtnClickHandler() {
        this.enroll.dialog = true
        const {image, title, author, publisher, isbn, description} = this.searchBooks.selection
        this.enroll.book = {
          image: this._strip(image),
          title: this._strip(title),
          author: this._strip(author),
          publisher: this._strip(publisher),
          isbn: this._strip(isbn),
          description: this._strip(description)
        }
      },
      
      async enrollRequest() {
        const request = this.searchBooks.selection
        console.log(request)
        try {
          const response = await createLibraryBook(request);
          console.log(response)
        } catch (e) {
          Message.error("등록 실패" + e)
        }
      },
      
      _strip(text) {
        return text.replace(/<\/?[^>]+(>|$)/g, "")
      }
    }
  }
</script>

<style scoped>
</style>
