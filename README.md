# 🗂️ HDFS 모의 시스템 실습 (Skeleton 코드 기반)

이 실습은 Hadoop HDFS의 구조를 Java로 직접 구현해보는 과제입니다.  
학생은 제공된 스켈레톤 코드에서 `NameNode`, `DataNode`, `DistributedFileSystem` 클래스의 일부 로직을 **직접 구현**해야 합니다.

---

## 💡 실습 목적

- HDFS의 **Master-Slave 아키텍처**를 코드로 이해하고 구현해본다.
- NameNode는 메타데이터를 관리하고, DataNode는 데이터를 분산 저장한다.
- 클라이언트는 파일을 저장(Write)하고 읽기(Read)할 수 있다.
- **복제(Replication)** 전략이 어떻게 구현되는지 체험한다.

---

## 🧱 실습 구성 파일 설명

| 파일명 | 역할 및 설명 |
|--------|--------------|
| `HDFS_Mock_Main.java` | 프로그램 실행 진입점. 클라이언트가 파일을 저장하고 읽는 테스트 실행 |
| `HDFSClient.java` | 클라이언트 역할. 파일을 쓰고 읽는 사용자 인터페이스 |
| `DistributedFileSystem.java` | HDFS 핵심 제어 로직. 데이터를 DataNode에 분산 저장하고 읽기 수행<br>📌 이 파일의 `writeFile()` 및 `readFile()` 로직 일부를 직접 채워야 합니다 |
| `NameNode.java` | Master 노드 역할. 파일 메타데이터를 저장하고 복제본을 어떤 DataNode에 저장할지 결정<br>📌 `assignDataNodes()`, `saveFileMetadata()` 등을 구현해야 합니다 |
| `DataNode.java` | Slave 노드 역할. 실제 데이터를 저장하고, 읽을 수 있게 제공<br>📌 `storeBlock()`, `getBlock()` 로직을 구현해야 합니다 |

---

## ✅ 과제에서 채워야 할 부분

학생은 다음 3개 파일의 빈칸(또는 TODO 주석)을 채워야 합니다:

- `NameNode.java`
    - `assignDataNodes(int count, List<DataNode>)` : 복제본을 저장할 DataNode들을 랜덤하게 선택
    - `saveFileMetadata(String filename, int count)` : 파일의 블록 수를 저장

- `DataNode.java`
    - `storeBlock(String blockId, String data)` : 블록 데이터를 저장
    - `getBlock(String blockId)` : 블록 데이터를 반환

- `DistributedFileSystem.java`
    - `writeFile(String filename, String data)` : 이름과 내용을 받아 복제하여 저장
    - `readFile(String filename)` : 저장된 내용을 블록 단위로 읽어 반환


---

## 🔁 복제 전략 설명

- 파일 하나가 저장되면, 해당 내용을 **3개의 서로 다른 DataNode**에 복제합니다.
- NameNode가 DataNode를 **랜덤으로 골라서** 복제 대상 노드 리스트를 구성합니다.

---

## 🚀 고도화 버전 참고 (선택)

더 실제와 가까운 구조를 구현해본 `고급 버전`은  
**`week03-hdfs-master` 브랜치**에 있습니다.

이 버전에서는 다음을 추가로 포함합니다:

- 블록 단위 분할 (`block_0`, `block_1`, ...)
- 블록마다 독립적인 복제 처리
- `NameNode`, `DataNode` 구성 고도화

> 복잡한 구조를 체험하고 싶은 학생은 이 브랜치를 참고하세요!

---

## 🙌 마무리

이 과제를 통해 HDFS의 기본 구조를 스스로 구성해보며, 다음을 익힐 수 있습니다:

- 분산 저장 시스템의 구조와 구성 요소의 역할
- 마스터-슬레이브 아키텍처 설계 원리
- 복제 전략의 중요성과 구현 방식
- 클라이언트 입장에서의 파일 저장 및 읽기 흐름

학습 중 막히는 부분이 있다면 `HDFS_Mock_Main.java`를 실행해보고 콘솔 출력을 꼼꼼히 살펴보세요.