<template>
    <v-list>
        <v-list-item
                v-for="item in tags"
                :key="item.id"
                @click=""
        >
            <v-list-item-content>
                <v-list-item-title v-text="item.name"></v-list-item-title>
            </v-list-item-content>
            <v-list-item-icon>
                <v-icon>mdi-bookmark-outline</v-icon>
            </v-list-item-icon>
        </v-list-item>
    </v-list>
</template>

<script>
    export default {
        name: 'TagList',

        props: {
            tags: Array,
        },

    }
</script>

<style scoped>

</style>
