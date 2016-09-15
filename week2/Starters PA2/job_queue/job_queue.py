# python3

class JobQueue:
    def read_data(self):
        self.num_workers, m = map(int, input().split())
        self.jobs = list(map(int, input().split()))
        # This is a list of list. Where each list contains the index of worker
        # and its next free time.
        self.workers = []
        assert m == len(self.jobs)

    def write_response(self):
        for i in range(len(self.jobs)):
          print(self.assigned_workers[i], self.start_times[i]) 
 

    def sift_up(self,idx):
      latest = self.workers[idx]

      # Getting index of parent node.
      parent = idx // 2
      if idx%2 == 0:
        parent -= 1

      if parent < 0:
        return

      if latest[1] < self.workers[parent][1] or latest[1] == self.workers[parent][1] and latest[0] < self.workers[parent][0]:
        self.workers[idx], self.workers[parent] = self.workers[parent], self.workers[idx]
        self.sift_up(parent)

    def sift_down(self,idx):
      minimum = idx
      workers = self.workers

      leftChild = 2*idx + 1
      if leftChild < len(workers) and (workers[leftChild][1] < workers[idx][1] or
        workers[leftChild][1] == workers[idx][1] and workers[leftChild][0] < workers[idx][0]):
        minimum = leftChild
  
      rightChild = 2*idx + 2
      if rightChild < len(workers) and (workers[rightChild][1] < workers[minimum][1] or
        workers[rightChild][1] == workers[minimum][1] and workers[rightChild][0] < workers[minimum][0]):
        minimum = rightChild

      if minimum != idx:
        workers[minimum], workers[idx] = workers[idx], workers[minimum]
        self.sift_down(minimum)

      self.workers = workers
    
    def insert(self,idx,next_free_time):
      self.workers.append([idx,next_free_time])
      self.sift_up(len(self.workers)-1)

    def update(self,nft):
      n = self.workers[0]
      print(n[0],n[1])
      n[1] += nft
      self.sift_down(0)
      return n

    def assign_jobs(self):
        # TODO: replace this code with a faster algorithm.
        for i in range(self.num_workers):
          self.insert(i,0)

        for i in self.jobs:
          self.update(i)

    def solve(self):
        self.read_data()
        self.assign_jobs()
        #self.write_response()

if __name__ == '__main__':
    job_queue = JobQueue()
    job_queue.solve()

