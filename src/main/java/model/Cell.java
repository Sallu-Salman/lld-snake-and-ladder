package model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class Cell {
    @Getter
    @NonNull
    private int id;

    @Setter
    @Getter
    private Shifter shifter;
}
