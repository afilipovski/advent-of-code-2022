def sign(n):
    if n > 0:
        return 1
    elif n < 0:
        return -1
    return 0


class Interval:
    contiguous = list()

    def __str__(self):
        res = ""
        for c in self.contiguous:
            res += f"{c}, "
        return res

    def __len__(self):
        res = 0
        for c in self.contiguous:
            res += c[1] - c[0] + 1
        return res

    def __contains__(self, item: int | float):
        for c in self.contiguous:
            if c[0] <= item <= c[1]:
                return True
        return False

    def append(self, other: tuple[int | float, int | float]):
        for c in self.contiguous:
            if (
                    c[0] <= other[0] <= c[1] or
                    c[0] <= other[1] <= c[1] or
                    (other[0] <= c[0] and c[1] <= other[1])
            ):
                self.contiguous.remove(c)
                new_c = min(c[0], other[0]), max(c[1], other[1])
                self.append(new_c)
                return
        self.contiguous.append(other)

    def intersection(self, other: tuple[int | float, int | float]):
        for c in self.contiguous:
            if (
                    c[0] <= other[0] <= c[1] or
                    c[0] <= other[1] <= c[1] or
                    (other[0] <= c[0] and c[1] <= other[1])
            ):
                self.contiguous.remove(c)
                new_c = max(c[0], other[0]), min(c[1], other[1])
                self.contiguous.append(new_c)
            else:
                self.contiguous.remove(c)
