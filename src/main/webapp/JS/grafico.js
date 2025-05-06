window.onload = function () {
    // Gráfico de estadísticas de mascotas
    var ctx = document.getElementById('petStatsChart').getContext('2d');
    var petStatsChart = new Chart(ctx, {
        type: 'pie', // Pie chart para distribución
        data: {
            labels: ['Perros', 'Gatos', 'Otros', 'No Mascotas'],
            datasets: [{
                    label: 'Distribución de Mascotas en los Hogares',
                    data: [60, 30, 5, 5], // Datos ficticios, puedes cambiar estos valores
                    backgroundColor: ['#36a2eb', '#ff6384', '#ffcd56', '#cccccc'],
                    borderColor: ['#36a2eb', '#ff6384', '#ffcd56', '#cccccc'],
                    borderWidth: 1
                }]
        },
        options: {
            responsive: true
        }
    });
    
        // Gráfico de enfermedades comunes en mascotas
        var ctx3 = document.getElementById('diseasePrevalenceChart').getContext('2d');
        var diseasePrevalenceChart = new Chart(ctx3, {
            type: 'bar', // Gráfico de barras
            data: {
                labels: ['Perros', 'Gatos', 'Aves'], // Tipos de animales
                datasets: [
                    {
                        label: 'Parásitos',
                        data: [40, 25, 10], // Porcentaje de prevalencia en perros, gatos y aves
                        backgroundColor: '#ff6384', // Color para la barra de parásitos
                        borderColor: '#ff6384',
                        borderWidth: 1
                    },
                    {
                        label: 'Obesidad',
                        data: [30, 35, 15], // Porcentaje de prevalencia en perros, gatos y aves
                        backgroundColor: '#36a2eb', // Color para la barra de obesidad
                        borderColor: '#36a2eb',
                        borderWidth: 1
                    },
                    {
                        label: 'Enfermedades Cardíacas',
                        data: [25, 30, 5], // Porcentaje de prevalencia en perros, gatos y aves
                        backgroundColor: '#ffcd56', // Color para la barra de enfermedades cardíacas
                        borderColor: '#ffcd56',
                        borderWidth: 1
                    },
                    {
                        label: 'Cáncer',
                        data: [15, 20, 5], // Porcentaje de prevalencia en perros, gatos y aves
                        backgroundColor: '#8e44ad', // Color para la barra de cáncer
                        borderColor: '#8e44ad',
                        borderWidth: 1
                    }
                ]
            },
            options: {
                responsive: true,
                scales: {
                    y: {
                        beginAtZero: true,
                        title: {
                            display: true,
                            text: 'Prevalencia (%)'
                        }
                    }
                }
            }
        });
        
};