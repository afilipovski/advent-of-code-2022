from collections import deque


def height(c):
    if c == 'S':
        return 0
    if c == 'E':
        return ord('Z') - ord('A')
    return ord(c) - ord('a')


def process_input(file_name: str):
    res = {}
    with open(file_name, 'r', encoding='utf-8') as f:
        raw = [line.strip() for line in f.readlines()]
        for i in range(len(raw)):
            for j in range(len(raw[0])):
                if raw[i][j] == 'S':
                    res["start"] = (i, j)
                if raw[i][j] == 'E':
                    res["end"] = (i, j)
        res["heightmap"] = [[height(c) for c in line] for line in raw]
    return res


data = process_input('input.txt')

start = data['start']
end = data['end']
heightmap = data['heightmap']


def valid_position(x, y):
    if x < 0 or y < 0:
        return False
    if x >= len(heightmap) or y >= len(heightmap[0]):
        return False
    return True


def steps_from(xs, ys):
    queue = deque([(xs, ys)])
    stepsmap = [[-1 for _ in range(len(heightmap[0]))] for _ in range(len(heightmap))]

    stepsmap[xs][ys] = 0

    while len(queue):
        x, y = queue.pop()
        # print(x,y)
        neighbors = [(x - 1, y), (x + 1, y), (x, y - 1), (x, y + 1)]
        for neighbor in neighbors:
            xn, yn = neighbor
            if not valid_position(xn, yn) or stepsmap[xn][yn] != -1:
                continue
            if heightmap[xn][yn] - heightmap[x][y] <= 1:
                stepsmap[xn][yn] = stepsmap[x][y] + 1
                queue.appendleft((xn, yn))
                if (xn, yn) == end:
                    return stepsmap[xn][yn]


print("P1: ", steps_from(start[0], start[1]))

p2 = 50000
for i in range(len(heightmap)):
    for j in range(len(heightmap[0])):
        if heightmap[i][j] == 0:
            pathlength = steps_from(i, j)
            if pathlength:
                p2 = min(p2, pathlength)

print("P2: ", p2)
