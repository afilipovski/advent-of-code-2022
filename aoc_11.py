import math


class Monkey:
    items: list[int] = list()
    operation: callable = lambda x: x
    divisor: int = 0
    recepient_true: int = 0
    recepient_false: int = 0
    inspections: int = 0

    def __str__(self):
        return f"{self.items=}\n{self.operation=}\n{self.divisor=}\n{self.recepient_true=}\n{self.recepient_false=}"


def parse_input(file_name: str) -> list[Monkey]:
    def heterogenous(operation: str, operand: int):
        if operation == '+':
            return lambda x: x + operand
        return lambda x: x * operand

    res = list[Monkey]()

    with open(file_name, 'r', encoding='utf-8') as f:
        lines = [line.strip() for line in f.readlines()]
        for line in lines:
            if 'Monkey' in line:
                res.append(Monkey())
            elif 'items' in line:
                parts = line.split(" ")[2:]
                parts = [item[0:-1] for item in parts[0:-1]] + parts[-1:]
                parts = [int(item) for item in parts]
                res[-1].items = parts
            elif 'Operation' in line:
                parts = line.split(" ")[-2:]
                if parts[1] == 'old':
                    res[-1].operation = lambda x: x ** 2
                else:
                    res[-1].operation = heterogenous(parts[-2], int(parts[-1]))
            elif 'divisible' in line:
                res[-1].divisor = int(line.split(" ")[-1])
            elif 'true' in line:
                res[-1].recepient_true = int(line.split(" ")[-1])
            elif 'false' in line:
                res[-1].recepient_false = int(line.split(" ")[-1])

    return res


def cycle(monkey_list: list[Monkey], dbt: bool):
    gcd = math.prod([mx.divisor for mx in monkey_list])

    for mx in monkey_list:
        mx.inspections += len(mx.items)
        if dbt:
            inspected_items = [mx.operation(item) // 3 for item in mx.items]
        else:
            inspected_items = [mx.operation(item) % gcd for item in mx.items]
        mx.items = []
        for inspected_item in inspected_items:
            if not inspected_item % mx.divisor:
                monkey_list[mx.recepient_true].items.append(inspected_item)
            else:
                monkey_list[mx.recepient_false].items.append(inspected_item)


listata = parse_input('input.txt')
for i in range(20):
    cycle(listata, True)
inspections_arr = sorted([mx.inspections for mx in listata], reverse=True)
print("P1:", inspections_arr[0] * inspections_arr[1])
listata = parse_input('input.txt')
for i in range(10000):
    cycle(listata, False)
inspections_arr = sorted([mx.inspections for mx in listata], reverse=True)
print("P2:", inspections_arr[0] * inspections_arr[1])
