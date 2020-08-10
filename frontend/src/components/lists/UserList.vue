<template>
    <v-list>
        <v-list-item
                v-for="item in users"
                :key="item.id"
                @click=""
        >
<!--            fixme-->
<!--            <v-list-item-icon>-->
<!--                <v-icon v-if="item.id === defaultAccount.owner.id" color="indigo">mdi-star</v-icon>-->
<!--            </v-list-item-icon>-->
            <v-list-item-content>
                <v-list-item-title v-text="item.name"></v-list-item-title>
            </v-list-item-content>

            <v-list-item-avatar>
                <v-img :src="item.userpic"></v-img>
            </v-list-item-avatar>
        </v-list-item>
    </v-list>
</template>

<script>
    export default {
        name: 'UserList',

        props: {
            users: Array,
        }
    }
</script>

<style scoped>

</style>