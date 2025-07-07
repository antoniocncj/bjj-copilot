export interface GraduationHistory {
  id: string;
  userId: string;
  instructorId: string;
  fromBelt: Belt;
  toBelt: Belt;
  graduationDate: string;
  notes?: string;
  createdAt: string;
  userName: string;
  instructorName: string;
}

export interface Belt {
  id: string;
  name: string;
  colorCode: string;
  displayName: string;
  sortOrder: number;
}

export interface CreateGraduationRequest {
  userId: string;
  toBeltId: string;
  notes?: string;
}