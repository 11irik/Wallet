<script>
    import {Pie} from 'vue-chartjs';

    export default {
        extends: Pie,

        props: {
            label: String,
            data: Array
        },

        mounted() {
            let colors = []
            for (let i in this.data[1]) {
                colors.push(this.getRandomColor());
            }

            this.renderChart(

                {
                    labels: this.data[0],

                    datasets: [
                        {
                            backgroundColor: colors,
                            data: this.data[1]
                        }
                    ]
                },
                {responsive: true, maintainAspectRatio: false}
            );
        },

        methods: {
            getRandomColor() {
                var letters = '0123456789ABCDEF';
                var color = '#';
                for (var i = 0; i < 6; i++) {
                    color += letters[Math.floor(Math.random() * 16)];
                }
                return color;
            },

        }
    };
</script>
