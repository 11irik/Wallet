<template>
    <v-container>
        <h1>Группа: {{defaultAccount.name}}</h1>

        <v-row>
            <v-col cols="12" md="4">
                <Users/>
            </v-col>

            <v-col cols="12" md="4">
                <TagList/>
            </v-col>

            <v-col cols="12" md="4">
                <Categories/>
            </v-col>
        </v-row>

    </v-container>
</template>

<script>
    import Users from '../components/Users.vue';
    import TagList from '../components/Tags.vue';
    import Categories from '../components/Categories.vue';
    import {mapState} from 'vuex';

    export default {
        name: 'AccountPage',

        components: {
            Users,
            TagList,
            Categories,
        },

        computed: {
            ...mapState({
                //todo rename default to selected
                defaultAccount: state => state.defaultAccount.defaultAccount,
            }),
        },
    };
</script>

<style scoped>

</style>
