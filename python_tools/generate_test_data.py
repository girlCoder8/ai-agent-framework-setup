from __future__ import annotations
import csv, uuid
from pathlib import Path
out=Path("src/test/resources/data/generated_users.csv")
out.parent.mkdir(parents=True,exist_ok=True)
with out.open("w",newline="") as f:
    w=csv.DictWriter(f,fieldnames=["user_id","email"]); w.writeheader()
    for _ in range(10):
        u=str(uuid.uuid4()); w.writerow({"user_id":u,"email":f"qa+{u[:8]}@example.test"})
print(out)
