<template>
    <v-list>
        <v-toolbar-title>Покупки</v-toolbar-title>
        <v-list-item
                v-for="item in items"
                :key="item.id"
                @click=""
        >
            <v-list-item-content>
                <v-list-item-title>Описание: {{item.name}}</v-list-item-title>
                <v-spacer></v-spacer>
                <v-list-item-title>цена: {{item.price}} р.</v-list-item-title>
                <v-list-item-subtitle v-if="item.tag">метка: {{item.tag.name}}
                </v-list-item-subtitle>
            </v-list-item-content>
        </v-list-item>
    </v-list>
</template>

<script>
    export default {
        name: 'ItemList',

        props: {
            items: Array
        }
    }
</script>

<style scoped>

</style>
