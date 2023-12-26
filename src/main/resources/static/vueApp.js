const{ createApp } = Vue

const baseURL = "http://localhost:9090/coin"

const mainContainer = {
	data(){
		return {
			coins: [],
			formCoin: {
				isNew: true,
				name: '',
				price: '',
				quantity: '',
				title: 'Cadastrar nova transação',
				button: 'Cadastrar'
			}
		}
	},
	mounted(){
		this.showAllCoins()
		console.log(formCoin.title)
	},
	
	methods: {
		showAllCoins(){
			axios
				.get(baseURL)
				.then(response =>{
					response.data.forEach(item =>{
						this.coins.push({
							name: item.name,
							quantity: item.quantity
						})
					})
				})
		}
	}
}

createApp(mainContainer).mount("#app")