from utils import sign


def generate_objects(file_name: str) -> set:
    res = set()

    def draw_line(t1, t2):
        while t1 != t2:
            res.add(t1)
            t1 = t1[0] + sign(t2[0] - t1[0]), t1[1] + sign(t2[1] - t1[1])
        res.add(t2)

    with open(file_name, 'r', encoding='utf-8') as f:
        entries = [line.strip().split(" -> ") for line in f.readlines()]
        entries = [[tuple(int(coordinate) for coordinate in point.split(",")) for point in entry] for entry in entries]
        for entry in entries:
            for i in range(len(entry) - 1):
                draw_line(entry[i], entry[i + 1])
    return res


data = generate_objects('input.txt')
abyss_y = max(*[p[1] for p in data])


def drop_sand(p2=False) -> bool:
    def valid_position(x, y):
        positions = [(x, y + 1), (x - 1, y + 1), (x + 1, y + 1)]
        if p2:
            positions = [p for p in positions if p[1] < abyss_y + 2]
        for p in positions:
            if p not in data_copy:
                return p

    sand = (500, 0)
    if sand in data_copy:
        return False

    while (next_position := valid_position(*sand)) is not None:
        if next_position[1] > abyss_y + 6:
            return False
        sand = next_position

    data_copy.add(sand)
    return True


data_copy = data.copy()
i = 0

while drop_sand():
    i = i + 1
print("P1:", i)

data_copy = data.copy()
i = 0
while drop_sand(True):
    i = i + 1
print("P2:", i)
