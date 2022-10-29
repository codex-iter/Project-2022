int snakeLadderCmd(int ply) {
  switch (ply) {
    // Snakes
    case 99:
      ply = 66;
      break;
    case 95:
      ply = 72;
      break;
    case 79:
      ply = 49;
      break;
    case 63:
      ply = 41;
      break;
    case 56:
      ply = 36;
      break;
    case 44:
      ply = 33;
      break;
    case 37:
      ply = 30;
      break;
    case 25:
      ply = 16;
      break;
    case 21:
      ply = 3;
      break;
    case 18:
      ply = 7;
      break;

    // Ladders
    case 5:
      ply = 14;
      break;
    case 20:
      ply = 29;
      break;
    case 23:
      ply = 45;
      break;
    case 40:
      ply = 48;
      break;
    case 42:
      ply = 53;
      break;
    case 58:
      ply = 67;
      break;
    case 70:
      ply = 90;
      break;
    case 71:
      ply = 92;
      break;
    case 75:
      ply = 97;
      break;

    default:
  }
  return ply;
}

int snakeLadderCmd2(int ply) {
  switch (ply) {
    // Snakes
    case 17:
      ply = 6;
      break;
    case 33:
      ply = 14;
      break;
    case 39:
      ply = 28;
      break;
    case 54:
      ply = 46;
      break;
    case 81:
      ply = 43;
      break;
    case 99:
      ply = 18;
      break;

    // Ladders
    case 84:
      ply = 95;
      break;
    case 47:
      ply = 86;
      break;
    case 50:
      ply = 59;
      break;
    case 2:
      ply = 43;
      break;
    default:
  }
  return ply;
}
