package model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public class Player {
    @NonNull
    private String name;
    @Setter
    private Cell currentCell;
}
