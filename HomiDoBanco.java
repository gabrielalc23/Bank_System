import random

# Constantes
TAXA_DE_JUROS = 0.02
SALDO_MINIMO = 100
TAXA_DE_TRANSACAO = 1

# Dicionário para armazenar informações da conta do usuário
contas = {}

class SistemaBancario:
    def __init__(self):
        self.menu()
        
    # Exibir menu principal
    def menu(self):
        while True:
            print("1. Criar uma conta")
            print("2. Entrar na conta")
            print("0. Sair")
            escolha = input()
            if escolha == "1":
                self.criar_conta()
            elif escolha == "2":
                self.entrar()
            elif escolha == "0":
                print("Tchau!")
                break
                
    # Criar uma nova conta
    def criar_conta(self):
        print("Digite seu nome:")
        nome = input()
        print("Digite seu deposito inicial:")
        saldo = float(input())
        if saldo < SALDO_MINIMO:
            print(f"Saldo mínimo é {SALDO_MINIMO}.")
            return
        # Gerar número de cartão e código pin único
        while True:
            numero_cartao = str(random.randint(1000000000, 9999999999))
            if numero_cartao not in contas:
                break
        pin = str(random.randint(1000, 9999))
        contas[numero_cartao] = {"nome": nome, "saldo": saldo, "pin": pin}
        print(f"Conta criada com sucesso. Seu número de cartão é {numero_cartao} e seu código pin é {pin}.")
        
    //Entrar em uma conta existente
    def entrar(self):
        print("Digite seu número de cartão:")
        numero_cartao = input()
        print("Digite seu código pin:")
        pin = input()
        if numero_cartao in contas and contas[numero_cartao]["pin"] == pin:
            self.menu_conta(numero_cartao)
        else:
            print("Número de cartão ou código pin inválido.")
            
    //Exibir menu da conta
    def menu_conta(self, numero_cartao):
        while True:
            print("1. Verificar saldo")
            print("2. Depositar")
            print("3. Sacar")
            print("4. Sair")
            escolha = input()
            if escolha == "1":
                self.verificar_saldo(numero_cartao)
            elif escolha == "2":
                self.depositar(numero_cartao)
            elif escolha == "3":
                self.sacar(numero_cartao)
            elif escolha == "4":
                print("Desconectado com sucesso.")
                break
                
    //Verificar saldo da conta
    def verificar_saldo(self, numero_cartao):
        print(f"Seu saldo é {contas[numero_cartao]['saldo']}.")
        
    //Depositar dinheiro na conta
    def depositar(self, numero_cartao):
        print("Digite o valor do depósito:")
        valor = float(input())
        contas[numero_cartao]['saldo'] += valor
        contas[numero_cartao]['saldo'] -= TAXA_DE_TRANSACAO
        print(f"Depósito de {valor} realizado com sucesso. Seu novo saldo é {contas[numero_cartao]['saldo']}.")
        
    //Sacar dinheiro da conta
    def sacar(self, numero_cartao):
        print("Digite o valor do saque:")
        valor = float(input())
        if valor > contas[numero_cartao]['saldo']:
            print("Saldo insuficiente.")
            return
        contas[numero_cartao]['saldo'] -= valor
        contas[numero_cartao]['saldo'] -= TAXA_DE_TRANSACAO
        print(f"Saque de {valor} realizado com sucesso. Seu novo saldo é {contas[numero_cartao]['saldo']}.")