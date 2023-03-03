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
            objects.add((p[0], p[1]))
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
else:
    file = 'input.txt'
    yd = 2000000

data = parse_input(file)
sensors = data["sensors"]

objects = data["objects"]
objects = set([b[0] for b in objects if b[1] == yd])

intercepts = set()
for s in sensors:
    i = 0
    while manhattan(s.x, s.y, s.x + i, yd) <= s.dist:
        if s.x - i not in objects:
            intercepts.add(s.x - i)
        if s.x + i not in objects:
            intercepts.add(s.x + i)
        i = i + 1
print("P1:", len(intercepts))
