export interface PizzaOrder {
    name: string
    email: string
    size: number
    base: string
    thickCrust: string
    sauce: string
    toppings: string[]
    comments?: string
}

export interface getOrderByEmail {
    orderId: string
    totalCost: number
}