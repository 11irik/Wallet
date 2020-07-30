<template>
    <v-container>
        <h1>Группа: {{defaultAccount.name}}</h1>

        <v-row>
            <v-col cols="12" md="4">
                <UserList></UserList>
            </v-col>

            <v-col cols="12" md="4">
                <TagList></TagList>
            </v-col>

            <v-col cols="12" md="4">
                <CategoryList></CategoryList>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
    import UserList from "../components/lists/UserList.vue";
    import TagList from "../components/lists/TagList.vue";
    import CategoryList from "../components/lists/CategoryList.vue";
    import {mapState} from "vuex";

    export default {
        name: "Home",

        components: {
            UserList,
            TagList,
            CategoryList,
        },

        data() {
            return {
            }
        },


        computed: {
            ...mapState({
                defaultAccount: state => state.defaultAccount.defaultAccount
            }),
        },

        methods: {

        }
    }


</script>

<style scoped>

</style>