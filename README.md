# 复刻拼图游戏 v1.0

> 基于 Java Swing 的 4×4 数字华容道拼图游戏，支持登录注册、键盘操控、步数统计。

## 快速开始

```bash
javac -encoding UTF-8 -d out src/APP.java src/puzzleGame/ui/*.java
java -cp out APP
```

要求 JDK 8+，确保项目根目录下有 `images/lusi/` 图片资源（含 `all.png` 和 `part_1.png` ~ `part_15.png`）。

## 功能

- 登录 / 注册界面，渐变背景 + 圆角 UI
- 4×4 拼图，图片打乱后通过方向键移动复原
- 步数实时统计，`A` 键查看完整图片提示
- `W` 键一键完成拼图（调试用）
- 菜单栏：重新开始、重新登录、关闭游戏

## 项目结构

```
├── src/
│   ├── APP.java                        # 程序入口
│   └── puzzleGame/ui/
│       ├── LoginJFrame.java            # 登录界面
│       ├── RegisterJFrame.java         # 注册界面
│       └── GameJFrame.java             # 游戏主界面
├── images/lusi/
│   ├── all.png                         # 完整图片（提示用）
│   └── part_1.png ... part_15.png      # 拼图碎片
└── png/                                # 截图展示
```

## 游戏操作

| 操作 | 按键 / 方式 |
|------|------------|
| 向右移动 | `←` 方向键 |
| 向左移动 | `→` 方向键 |
| 向上移动 | `↓` 方向键 |
| 向下移动 | `↑` 方向键 |
| 查看提示 | `A` 键 |
| 一键完成 | `W` 键 |

## 截图

- 登录页面 — `png/登录.png`
- 注册页面 — `png/注册.png`
- 游戏开始 — `png/开始游戏.png`
- 游戏通关 — `png/结束游戏.png`
- 图片提示 — `png/图片提示.png`

## 参考

教程来源：黑马程序员

---

创建者：软件工程252-侯亭彧 | 创建时间：2025.9
