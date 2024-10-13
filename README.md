# nas-tools

非官方项目，添加一些私有需求。

```yaml
version: '3.1'
services:
  nas-tools:
    image: mhmzx/nas-tools:latest
    ports:
      - 3000:3000
    volumes:
      - ./config:/config
      - ./.cache:/.cache
    environment:
      PUID: 1000
      PGID: 1000
```