<template>
  <li :class="{ completed: todo.done, editing: editing }" class="todo">
    <div class="view">
      <input
        :checked="todo.done"
        @change="toggleTodo( todo)"
        class="toggle"
        type="checkbox"
      >
      <label @dblclick="editing = true" v-text="todo.text"/>
      <button @click="deleteTodo( todo )" class="destroy"/>
    </div>
    <input
      :value="todo.text"
      @blur="doneEdit"
      @keyup.enter="doneEdit"
      @keyup.esc="cancelEdit"
      class="edit"
      v-focus="editing"
      v-show="editing"
    >
  </li>
</template>

<script>
  export default {
    name: 'Todo',
    directives: {
      focus(el, {value}, {context}) {
        if (value) {
          context.$nextTick(() => {
            el.focus()
          })
        }
      }
    },
    props: {
      todo: {
        type: Object,
        default: function () {
          return {}
        }
      }
    },
    data() {
      return {
        editing: false
      }
    },
    methods: {
      deleteTodo(todo) {
        this.$emit('deleteTodo', todo)
      },
      editTodo({todo, value}) {
        this.$emit('editTodo', {todo, value})
      },
      toggleTodo(todo) {
        this.$emit('toggleTodo', todo)
      },
      doneEdit(e) {
        const value = e.target.value.trim()
        const {todo} = this
        if (!value) {
          this.deleteTodo({
            todo
          })
        } else if (this.editing) {
          this.editTodo({
            todo,
            value
          })
          this.editing = false
        }
      },
      cancelEdit(e) {
        e.target.value = this.todo.text
        this.editing = false
      }
    }
  }
</script>
