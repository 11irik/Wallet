<template>
    <v-list>

        <v-list-item
                v-for="item in categories"
                :key="item.id"
                @click="onItemClick(item)"
        >
            <v-list-item-content>
                <v-list-item-title v-text="item.name"></v-list-item-title>
            </v-list-item-content>

            <v-list-item-icon>
                <v-icon>mdi-format-align-justify</v-icon>
            </v-list-item-icon>
        </v-list-item>

    </v-list>
</template>

<script>
    export default {
        name: 'CategoryList',

        props: {
            categories: Array,
            onItemClick: Function
        },
    }
</script>

<style scoped>

</style>
