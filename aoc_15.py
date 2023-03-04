from utils import Interval


def manhattan(xa, ya, xb, yb):
    return abs(xa - xb) + abs(ya - yb)


class Sensor:
    def __init__(self, x, y, xb, yb):
        self.x = x
        self.y = y
        self.dist = manhattan(x, y, xb, yb)

    def __str__(self) -> str:
        return f"{self.x}, {self.y}, {self.dist}"


def parse_input(file_name):
    objects = set()
    with open(file_name, 'r', encoding='utf-8') as f:
        parts = [line.split() for line in f.readlines()]
        parts = [[entry[2][2:-1], entry[3][2:-1], entry[-2][2:-1], entry[-1][2:]] for entry in parts]
        parts = [[int(p) for p in entry] for entry in parts]
        for p in parts:
            objects.add((p[-2], p[-1]))
        sensors = [Sensor(*p) for p in parts]
    return {
        "sensors": sensors,
        "objects": objects
    }


example = False
if example:
    file = 'example.txt'
    yd = 10
    block = 20
else:
    file = 'input.txt'
    yd = 2000000
    block = 4000000

data = parse_input(file)
sensors = data["sensors"]

objects = data["objects"]
objects = set([b[0] for b in objects if b[1] == yd])


def interval_at_y(y: int) -> Interval:
    interval = Interval()
    interval.contiguous = []
    for s in sensors:
        sway = s.dist - abs(y - s.y)
        # print(sway)
        if sway >= 0:
            contiguous = s.x - sway, s.x + sway
            interval.append(contiguous)
    # print(interval)
    return interval


p1_int = interval_at_y(yd)
i = len(p1_int)
for o in objects:
    if o in p1_int:
        i -= 1
print("P1:", i)

p2 = 0
for i in range(block):
    row_int = interval_at_y(i)
    row_int.intersection((0, block))
    if len(row_int) != block + 1:  # found the row
        for j in range(block):
            if j not in row_int:
                p2 = 4000000 * j + i
                break
    if p2:
        break
print("P2:",p2)
