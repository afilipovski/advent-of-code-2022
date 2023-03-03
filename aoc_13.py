def parse_packet(serial):
    res = list()
    contents = serial[1:-1]

    stack = list()

    begin = 0
    for i in range(len(contents)):
        if contents[i] == ',' and len(stack) == 0 and i > begin:
            res.append(int(contents[begin:i]))
            begin = i + 1
        if contents[i] == '[':
            stack.append(contents[i])
            if len(stack) == 0:
                begin = i
        if contents[i] == ']':
            stack.pop()
            if len(stack) == 0:
                res.append(parse_packet(contents[begin:i + 1]))
                begin = i + 2
    if begin < len(contents):
        res.append(int(contents[begin:]))
    return res


def pio(p1, p2):  # packets in order
    for i in range(len(p1)):
        if i >= len(p2):  # right side ran out of items
            return False
        if isinstance(p1[i], int) and isinstance(p2[i], int):
            if p1[i] != p2[i]:
                return p1[i] < p2[i]
        elif isinstance(p1[i], list) and isinstance(p2[i], list):
            x = pio(p1[i], p2[i])
            if x is not None:
                return x
        else:
            op1 = p1[i]
            op2 = p2[i]
            if isinstance(p1[i], int):
                op1 = [p1[i]]
            if isinstance(p2[i], int):
                op2 = [p2[i]]
            x = pio(op1, op2)
            if x is not None:
                return x
    if len(p1) < len(p2):
        return True
    return None


with open('input.txt', 'r', encoding='utf-8') as f:
    packets = [parse_packet(line.strip()) for line in f.readlines() if line.strip()]

p1 = 0
for i in range(0, len(packets), 2):
    if pio(packets[i], packets[i + 1]):
        p1 += i // 2 + 1
print("P1:", p1)

dividers = [[2]], [[6]]
packets.extend(dividers)
for i in range(len(packets)):
    for j in range(len(packets) - i - 1):
        if not pio(packets[j], packets[j + 1]):
            packets[j], packets[j + 1] = packets[j + 1], packets[j]

p2 = 1
for i in range(len(packets)):
    if packets[i] in dividers:
        p2 *= (i + 1)
print("P2:", p2)
