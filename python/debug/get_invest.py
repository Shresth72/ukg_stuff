def get_current_price(coin):
    print(f"Getting current price of {coin}")
    if coin == "bitcoin":
        return 10000
    elif coin == "etherium":
        return 1000
    elif coin == "solana":
        return 100
    else:
        return -1


def get_investment_info(investment):
    name = investment["name"]
    quantity = investment["quantity"]

    # import ipdb; ipdb.set_trace()

    # import pdb; pdb.set_trace()
    # breakpoint()

    current_price = get_current_price(name)
    print(f"The current value of your {quantity} {name} is {current_price * quantity}.")


if __name__ == "__main__":
    portfolio = [
        {"name": "bitcoin", "quantity": 1.3, "buy": True},
        {"name": "etherium", "quantity": 13.4, "buy": True},
        {"name": "solana", "quantity": 134.3, "buy": True},
    ]

    for investment in portfolio:
        get_investment_info(investment)
